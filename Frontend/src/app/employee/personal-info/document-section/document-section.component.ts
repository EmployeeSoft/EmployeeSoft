import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {UserInfoNameService} from '../../_services/user-info/user-info-name.service';
import {AccountService, AlertService} from '../../../common/_services';
import {JwtHelperService} from '@auth0/angular-jwt';
import {HttpErrorResponse, HttpEvent, HttpEventType} from '@angular/common/http';
// @ts-ignore
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-document-section',
  templateUrl: './document-section.component.html',
  styleUrls: ['./document-section.component.css']
})
export class DocumentSectionComponent implements OnInit {
  documentData: any;
  SecEdit: boolean;
  userId: any;
  hasI20: boolean;
  hasI983: boolean;
  hasI983Signed: boolean;
  hasOPT: boolean;
  hasOPTReceipt: boolean;
  hasSTEM: boolean;
  hasSTEMReceipt: boolean;
  optReceiptFile: File = {} as File;
  optEadFile: File = {} as File;
  i983File: File = {} as File;
  i20File: File = {} as File;
  optStemReceiptFile: File = {} as File;
  optStemEadFile: File = {} as File;
  hasFile = false;
  noFileError = false;

  constructor(
    private fb: FormBuilder,
    private documentService: UserInfoNameService,
    private alertService: AlertService,
    private accountService: AccountService,
  ) { }

  ngOnInit(): void {
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    this.userId = decodedJwt.sub.toString();

    setTimeout(
      () => {this.documentService.getDocumentData(this.userId)
        .subscribe(data => {
          console.log(data);
          this.documentData = data;
          this.documentData = this.fb.group({
            I20: this.documentData.personalDocuments['I-20'],
            I983: this.documentData.personalDocuments['I-983'],
            I983Signed: this.documentData.personalDocuments['I-983 Signed'],
            Opt: this.documentData.personalDocuments['OPT EAD'],
            OptReceipt: this.documentData.personalDocuments['OPT Receipt'],
            OptStemEAD: this.documentData.personalDocuments['OPT STEM EAD'],
            OptStemReceipt: this.documentData.personalDocuments['OPT STEM Receipt'],
          });
          console.log(this.documentData.controls);
          this.hasI20 = this.documentData.controls.I20.value != null;
          this.hasI983 = this.documentData.controls.I983.value != null;
          this.hasI983Signed = this.documentData.controls.I983Signed.value != null;
          this.hasOPT = this.documentData.controls.Opt.value != null;
          this.hasOPTReceipt = this.documentData.controls.OptReceipt.value != null;
          this.hasSTEM = this.documentData.controls.OptStemEAD.value != null;
          this.hasSTEMReceipt = this.documentData.controls.OptStemReceipt.value != null;
        }, error => console.log(error)); }
      , 0);

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
    );
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

  setI20(event: any) {
    this.i20File = event.target.files[0];
  }

  setI983(event: any) {
    this.i983File = event.target.files[0];
    this.hasFile = true;
  }

  setOptEad(event: any) {
    this.optEadFile = event.target.files[0];
    this.hasFile = true;
  }

  setOptReceipt(event: any) {
    this.optReceiptFile = event.target.files[0];
    this.hasFile = true;
  }

  setOptStemEad(event: any) {
    this.optStemEadFile = event.target.files[0];
    this.hasFile = true;
  }

  setOptStemReceipt(event: any) {
    this.optStemReceiptFile = event.target.files[0];
    this.hasFile = true;
  }

  startEdit() {
    this.SecEdit = true;
  }

  endEdit() {
    this.SecEdit = false;
    this.alertService.warn('Are you sure to discard all changes?')
  }

  onSubmit() {
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
}
