import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { EmployeeVisa } from '../../common/_models';
import {AccountService, AlertService} from '../../common/_services';
import { EmployeeVisaService } from '../_services/employee-visa.service';
import {HttpErrorResponse, HttpEvent, HttpEventType} from '@angular/common/http';
// @ts-ignore
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-employee-visa-status-management',
  templateUrl: './employee-visa-status-management.component.html',
  styleUrls: ['./employee-visa-status-management.component.css']
})
export class EmployeeVisaStatusManagementComponent implements OnInit {

  documents: string[][];
  showDocuments: any[] = [];

//  employeeVisa: EmployeeVisa;
  data: any;

  employeeVisa = {
    hasOptReceipt: false,
    hasUploadedOptEad: false,
    isOptEadLessThan100Days: false,
    hasUploadedFormI983: false,
    hasFormI983HrSignedAndApproved: false,
    hasUploadedFormI20: false,
    hasUploadedOptStemReceipt: false,
    hasUploadedOptStemEad: false,
    AwsUrlOptReceipt: '',
    AwsUrlOptEad: '',
    AwsUrlFormI983: '',
    AwsUrlFormI983Signed: '',
    AwsUrlFormI20: '',
    AwsUrlFormOptStemReceipt: '',
    AwsUrlFormOptStemEad: '',
    FileNameOptReceipt: '',
    FileNameOptEad: '',
    FileNameFormI983: '',
    FileNameFormI983Signed: '',
    FileNameFormI20: '',
    FileNameFormOptStemReceipt: '',
    FileNameFormOptStemEad: '',
  };

  userId: string;
  optReceiptFile: File = {} as File;
  optEadFile: File = {} as File;
  i983File: File = {} as File;
  i20File: File = {} as File;
  optStemReceiptFile: File = {} as File;
  optStemEadFile: File = {} as File;
  hasFile = false;
  noFileError = false;

  constructor(private alertService: AlertService,
              private employeeVisaService: EmployeeVisaService,
              private accountService: AccountService) { }

  ngOnInit(): void {
    console.log(this.employeeVisa);
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    this.userId = decodedJwt.sub.toString();
    const user = JSON.parse(localStorage.getItem('user')!);

    setTimeout(
      () => {this.employeeVisaService.getVisaStatus(this.userId, user.role)
        .subscribe(data => {
            console.log(data);
            this.data = data;
            // console.log(this.data.personalDocuments['OPT Receipt'].split('/').pop());
            // console.log(this.data.personalDocuments['OPT Receipt']);
            // console.log(this.data.personalDocuments['OPT EAD']);
            // console.log(this.data.lessThan100Days);
            // console.log(this.data.personalDocuments['I-983']);
            // console.log(this.data.personalDocuments['I-983 Signed']);
            // console.log(this.data.personalDocuments['I-20']);
            // console.log(this.data.personalDocuments['OPT STEM Receipt']);
            // console.log(this.data.personalDocuments['OPT STEM EAD']);

            if (this.data.personalDocuments['OPT Receipt']) {
              this.employeeVisa.hasOptReceipt = true;
              this.employeeVisa.AwsUrlOptReceipt = this.data.personalDocuments['OPT Receipt'];
              this.employeeVisa.FileNameOptEad = this.data.personalDocuments['OPT Receipt'].split('/').pop();
            }
            if (this.data.personalDocuments['OPT EAD']) {
              this.employeeVisa.hasUploadedOptEad = true;
              this.employeeVisa.AwsUrlOptEad = this.data.personalDocuments['OPT EAD'];
              this.employeeVisa.FileNameOptEad = this.data.personalDocuments['OPT EAD'].split('/').pop();
            }
            if (this.data.lessThan100Days) { this.employeeVisa.isOptEadLessThan100Days = true; }
            if (this.data.personalDocuments['I-983']) {
              this.employeeVisa.hasUploadedFormI983 = true;
              this.employeeVisa.AwsUrlFormI983 = this.data.personalDocuments['I-983'];
              this.employeeVisa.FileNameFormI983 =  this.data.personalDocuments['I-983'].split('/').pop();
            }
            if (this.data.personalDocuments['I-983 Signed']) {
              this.employeeVisa.hasUploadedFormI983 = true;
              this.employeeVisa.AwsUrlFormI983Signed = this.data.personalDocuments['I-983 Signed'];
              this.employeeVisa.FileNameFormI983Signed = this.data.personalDocuments['I-983 Signed'].split('/').pop();
            }
            if (this.data.personalDocuments['I-20']) {
              this.employeeVisa.hasFormI983HrSignedAndApproved = true;
              this.employeeVisa.AwsUrlFormI20 = this.data.personalDocuments['I-20'];
              this.employeeVisa.FileNameFormI20 = this.data.personalDocuments['I-20'];
            }
            if (this.data.personalDocuments['OPT STEM Receipt']) {
              this.employeeVisa.hasUploadedOptStemReceipt = true;
              this.employeeVisa.AwsUrlFormOptStemReceipt = this.data.personalDocuments['OPT STEM Receipt'];
              this.employeeVisa.FileNameFormOptStemReceipt = this.data.personalDocuments['OPT STEM Receipt'].split('/').pop();
            }
            if (this.data.personalDocuments['OPT STEM EAD']) {
              this.employeeVisa.hasUploadedOptStemEad = true;
              this.employeeVisa.AwsUrlFormOptStemEad = this.data.personalDocuments['OPT STEM EAD'];
              this.employeeVisa.FileNameFormOptStemEad = this.data.personalDocuments['OPT STEM EAD'].split('/').pop();
            }

            this.documents = [
              ['OPT Receipt', this.changeBoolStr(this.employeeVisa.hasOptReceipt), this.employeeVisa.FileNameOptEad, this.employeeVisa.AwsUrlOptEad],
              ['OPT EAD', this.changeBoolStr(this.employeeVisa.hasUploadedOptEad), this.employeeVisa.FileNameOptEad, this.employeeVisa.AwsUrlOptEad],
              ['Form I-983 submitted to HR', this.changeBoolStr(this.employeeVisa.hasUploadedFormI983), this.employeeVisa.FileNameFormI983, this.employeeVisa.AwsUrlFormI983],
              ['Form I-983 signed and approved by HR', this.changeBoolStr(this.employeeVisa.hasFormI983HrSignedAndApproved), this.employeeVisa.FileNameFormI983Signed, this.employeeVisa.AwsUrlFormI983Signed],
              ['Form I-20', this.changeBoolStr(this.employeeVisa.hasUploadedFormI20), this.employeeVisa.FileNameFormI983, this.employeeVisa.AwsUrlFormI20],
              ['OPT STEM Receipt', this.changeBoolStr(this.employeeVisa.hasUploadedOptStemReceipt), this.employeeVisa.FileNameFormOptStemReceipt, this.employeeVisa.AwsUrlFormOptStemReceipt],
              ['OPT STEM EAD', this.changeBoolStr(this.employeeVisa.hasUploadedOptStemEad), this.employeeVisa.FileNameFormOptStemEad, this.employeeVisa.AwsUrlFormOptStemEad]
            ];

            for (let i = 0; i < this.documents.length; i++) {
              if (this.documents[i][1] === 'INCOMPLETE') {
                if (!this.employeeVisa.isOptEadLessThan100Days) {
                  break;
                }
                this.showDocuments.push(this.documents[i]);
                break;
              }
              this.showDocuments.push(this.documents[i]);
              // console.log(this.documents);
              // console.log(this.showDocuments);
            }
            // console.log(this.employeeVisa);
            this.showCurrentStatusNotification();
          }, error => console.log(error)); }
      , 0);

  }

  showCurrentStatusNotification() {
    let msg = '';
    let warnMsg = '';
    switch (true) {
        case (!this.employeeVisa.hasOptReceipt):
        msg = 'Please upload OPT Receipt';
        break;

        case (this.employeeVisa.hasOptReceipt && !this.employeeVisa.hasUploadedOptEad):
        msg = 'Please upload OPT EAD';
        break;

        case (this.employeeVisa.hasUploadedOptEad && this.employeeVisa.isOptEadLessThan100Days && !this.employeeVisa.hasUploadedFormI983):
        msg = 'Download and fill I-983';
        warnMsg = 'Your OPT is expiring in less than 100 days';
        break;

        case (this.employeeVisa.hasUploadedOptEad && !this.employeeVisa.isOptEadLessThan100Days && this.employeeVisa.hasUploadedFormI983):
        msg = 'COMPLETE';
        break;

        case (this.employeeVisa.hasUploadedFormI983 && !this.employeeVisa.hasFormI983HrSignedAndApproved):
        msg = 'Waiting for HR to approve and sign I-983';
        break;

        case (this.employeeVisa.hasFormI983HrSignedAndApproved && !this.employeeVisa.hasUploadedFormI20):
        msg = 'Please send the I-983 with all necessary documents to your school and upload the new I-20';
        break;

        case (this.employeeVisa.hasFormI983HrSignedAndApproved && this.employeeVisa.hasUploadedFormI20 && !this.employeeVisa.hasUploadedOptStemReceipt):
        msg = 'Please upload your OPT STEM Receipt';
        break;

        case (this.employeeVisa.hasUploadedOptStemReceipt && !this.employeeVisa.hasUploadedOptStemEad):
        msg = 'Please upload your OPT STEM EAD';
        break;

        default:
        msg = 'COMPLETE';
    }

    if (msg === 'COMPLETE') {
        this.alertService.info(msg);
    } else {
        this.alertService.warn(msg);
    }

    if (warnMsg) {
      this.alertService.error(warnMsg);
    }
}
  changeBoolStr(bool: boolean): string {
    if (bool) {
      return 'COMPLETE';
    }
    return 'INCOMPLETE';
  }

  reportProgress(event: HttpEvent<any>, filename: string) {
    switch (event.type) {
      case HttpEventType.UploadProgress:
        console.log('upload progress');
        break;
      case HttpEventType.DownloadProgress:
        console.log('download progress');
        break;
      case HttpEventType.ResponseHeader:
        console.log('header');
        break;
      case HttpEventType.Response:
        const byteCharacters = atob(event.body.file);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray], {type: 'application/pdf'});
        const objectURL = URL.createObjectURL(blob);
        console.log(objectURL);
        saveAs(objectURL, filename);
        break;
      default:
        console.log(event);
    }
  }

  downloadI983() {
    const filename = 'I-983 Form';
    this.accountService.download(filename, '0').subscribe(
      event => {
        console.log(event);
        this.reportProgress(event, filename);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  downloadI983Sample() {
    const filename = 'I-983 Sample';
    this.accountService.download(filename, '0').subscribe(
      event => {
        console.log(event);
        this.reportProgress(event, filename);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  setOptReceipt(event: any) {
    this.optReceiptFile = event.target.files[0];
    this.hasFile = true;
  }

  setOptEad(event: any) {
    this.optEadFile = event.target.files[0];
    this.hasFile = true;
  }

  setI983(event: any) {
    this.i983File = event.target.files[0];
    this.hasFile = true;
  }

  setI20(event: any) {
    this.i20File = event.target.files[0];
    this.hasFile = true;
  }

  setOptStemReceipt(event: any) {
    this.optStemReceiptFile = event.target.files[0];
    this.hasFile = true;
  }

  setOptStemEad(event: any) {
    this.optStemEadFile = event.target.files[0];
    this.hasFile = true;
  }

  uploadFile() {
    if (!this.hasFile) {
      this.noFileError = true;
      return;
    } else {
      this.noFileError = false;

      // Upload logic
      let file = {} as File;
      let title = '';
      if (this.optReceiptFile !== null) {
        file = this.optReceiptFile;
        title = 'OPT Receipt';
      } else if (this.optEadFile !== null) {
        file = this.optEadFile;
        title = 'OPT EAD';
      } else if (this.i983File !== null) {
        file = this.i983File;
        title = 'I-983 Signed';
      } else if (this.i20File !== null) {
        file = this.i20File;
        title = 'I-20';
      } else if (this.optStemReceiptFile !== null) {
        file = this.optStemReceiptFile;
        title = 'OPT STEM Receipt';
      } else {
        file = this.optStemEadFile;
        title = 'OPT STEM EAD';
      }
      const formData = new FormData();
      formData.append('file', file, file.name);
      formData.append('userId', this.userId);
      formData.append('uploadTo', 'personal document');
      formData.append('fileTitle', title);
      this.accountService.upload(formData).subscribe(
        event => {
          console.log(event);
        },
        (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
    }
  }

  downloadUserFile(document: string) {
    let filename = '';
    if (document === 'OPT Receipt') {
      filename = 'OPT Receipt';
    } else if (document === 'OPT EAD') {
      filename = 'OPT EAD';
    } else if (document === 'Form I-983 submitted to HR') {
      filename = 'I-983';
    } else if (document === 'Form I-983 signed and approved by HR') {
      filename = 'I-983 Signed';
    } else if (document === 'Form I-20') {
      filename = 'I-20';
    } else if (document === 'OPT STEM Receipt') {
      filename = 'OPT STEM Receipt';
    } else {
      filename = 'OPT STEM EAD';
    }
    this.accountService.download(filename, this.userId).subscribe(
      event => {
        console.log(event);
        this.reportProgress(event, filename);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    )
  }
}
