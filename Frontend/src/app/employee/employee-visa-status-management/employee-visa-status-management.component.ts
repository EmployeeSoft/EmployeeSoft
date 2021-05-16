import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { EmployeeVisa } from '../../common/_models';
import { AlertService } from '../../common/_services';
import { EmployeeVisaService } from '../_services/employee-visa.service'

@Component({
  selector: 'app-employee-visa-status-management',
  templateUrl: './employee-visa-status-management.component.html',
  styleUrls: ['./employee-visa-status-management.component.css']
})
export class EmployeeVisaStatusManagementComponent implements OnInit {

  documents: string[][];
  showDocuments: any[] =[];

//  employeeVisa: EmployeeVisa;
  data: any;

  employeeVisa = {
    "hasOptReceipt": false,
    "hasUploadedOptEad": false,
    "isOptEadLessThan100Days": false,
    "hasUploadedFormI983": false,
    "hasFormI983HrSignedAndApproved": false,
    "hasUploadedFormI20": false,
    "hasUploadedOptStemReceipt": false,
    "hasUploadedOptStemEad": false,
    "AwsUrlOptReceipt": "",
    "AwsUrlOptEad": "",
    "AwsUrlFormI983": "",
    "AwsUrlFormI983Signed": "",
    "AwsUrlFormI20": "",
    "AwsUrlFormOptStemReceipt": "",
    "AwsUrlFormOptStemEad": "",
    "FileNameOptReceipt": "",
    "FileNameOptEad": "",
    "FileNameFormI983": "",
    "FileNameFormI983Signed": "",
    "FileNameFormI20": "",
    "FileNameFormOptStemReceipt": "",
    "FileNameFormOptStemEad": "",
  }

  constructor(private alertService: AlertService, private employeeVisaService: EmployeeVisaService) { }

  ngOnInit(): void {
    console.log(this.employeeVisa);
    const jwt = localStorage.getItem('jwt');
    const helper = new JwtHelperService();
    const decodedJwt = helper.decodeToken(jwt!);
    const userId = decodedJwt.sub.toString();
    const user = JSON.parse(localStorage.getItem('user')!);

    setTimeout(
      ()=> {this.employeeVisaService.getVisaStatus(userId, user.role)
        .subscribe(data=>{
            console.log(data);
            this.data = data;
            console.log(this.data.personalDocuments["OPT Receipt"].split("/").pop());
            console.log(this.data.personalDocuments["OPT Receipt"]);
            console.log(this.data.personalDocuments["OPT EAD"]);
            console.log(this.data.lessThan100Days);
            console.log(this.data.personalDocuments["I-983"]);
            console.log(this.data.personalDocuments["I-983 Signed"]);
            console.log(this.data.personalDocuments["I-20"]);
            console.log(this.data.personalDocuments["OPT STEM Receipt"]);
            console.log(this.data.personalDocuments["OPT STEM EAD"]);

            if (this.data.personalDocuments["OPT Receipt"]) { this.employeeVisa.hasOptReceipt = true; this.employeeVisa.AwsUrlOptReceipt = this.data.personalDocuments["OPT Receipt"]; this.employeeVisa.FileNameOptEad = this.data.personalDocuments["OPT Receipt"].split("/").pop();}
            if (this.data.personalDocuments["OPT EAD"]) { this.employeeVisa.hasUploadedOptEad = true; this.employeeVisa.AwsUrlOptEad = this.data.personalDocuments["OPT EAD"]; this.employeeVisa.FileNameOptEad = this.data.personalDocuments["OPT EAD"].split("/").pop();}
            if (this.data.lessThan100Days) { this.employeeVisa.isOptEadLessThan100Days = true;}
            if (this.data.personalDocuments["I-983"]) { this.employeeVisa.hasUploadedFormI983 = true; this.employeeVisa.AwsUrlFormI983 = this.data.personalDocuments["I-983"]; this.employeeVisa.FileNameFormI983 =  this.data.personalDocuments["I-983"].split("/").pop();}
            if (this.data.personalDocuments["I-983 Signed"]) { this.employeeVisa.hasUploadedFormI983 = true; this.employeeVisa.AwsUrlFormI983Signed = this.data.personalDocuments["I-983 Signed"]; this.employeeVisa.FileNameFormI983Signed = this.data.personalDocuments["I-983 Signed"].split("/").pop();}
            if (this.data.personalDocuments["I-20"]) { this.employeeVisa.hasFormI983HrSignedAndApproved = true; this.employeeVisa.AwsUrlFormI20 = this.data.personalDocuments["I-20"]; this.employeeVisa.FileNameFormI20 = this.data.personalDocuments["I-20"];}
            if (this.data.personalDocuments["OPT STEM Receipt"]) { this.employeeVisa.hasUploadedOptStemReceipt = true; this.employeeVisa.AwsUrlFormOptStemReceipt = this.data.personalDocuments["OPT STEM Receipt"]; this.employeeVisa.FileNameFormOptStemReceipt = this.data.personalDocuments["OPT STEM Receipt"].split("/").pop();}
            if (this.data.personalDocuments["OPT STEM EAD"]) { this.employeeVisa.hasUploadedOptStemEad = true; this.employeeVisa.AwsUrlFormOptStemEad = this.data.personalDocuments["OPT STEM EAD"]; this.employeeVisa.FileNameFormOptStemEad = this.data.personalDocuments["OPT STEM EAD"].split("/").pop();}

            this.documents = [
              ["OPT Receipt", this.changeBoolStr(this.employeeVisa.hasOptReceipt), this.employeeVisa.FileNameOptEad, this.employeeVisa.AwsUrlOptEad],
              ["OPT EAD", this.changeBoolStr(this.employeeVisa.hasUploadedOptEad), this.employeeVisa.FileNameOptEad, this.employeeVisa.AwsUrlOptEad],
              ["Form I-983 submitted to HR", this.changeBoolStr(this.employeeVisa.hasUploadedFormI983), this.employeeVisa.FileNameFormI983, this.employeeVisa.AwsUrlFormI983],
              ["Form I-983 signed and approved by HR", this.changeBoolStr(this.employeeVisa.hasFormI983HrSignedAndApproved), this.employeeVisa.FileNameFormI983Signed, this.employeeVisa.AwsUrlFormI983Signed],
              ["Form I-20", this.changeBoolStr(this.employeeVisa.hasUploadedFormI20), this.employeeVisa.FileNameFormI983, this.employeeVisa.AwsUrlFormI20],
              ["OPT STEM Receipt", this.changeBoolStr(this.employeeVisa.hasUploadedOptStemReceipt), this.employeeVisa.FileNameFormOptStemReceipt, this.employeeVisa.AwsUrlFormOptStemReceipt],
              ["OPT STEM EAD", this.changeBoolStr(this.employeeVisa.hasUploadedOptStemEad), this.employeeVisa.FileNameFormOptStemEad, this.employeeVisa.AwsUrlFormOptStemEad]
            ]

            for (let i = 0; i < this.documents.length; i++) {
              if (this.documents[i][1] === "INCOMPLETE") {
                if (!this.employeeVisa.isOptEadLessThan100Days) {
                  break;
                }
                this.showDocuments.push(this.documents[i]);
                break;
              }
              this.showDocuments.push(this.documents[i]);
              console.log(this.documents)
              console.log(this.showDocuments)
            }
            console.log(this.employeeVisa)
            this.showCurrentStatusNotification();
          }, error=> console.log(error));}
      ,0);

  }

  showCurrentStatusNotification() {
    let msg: string = "";
    let warnMsg: string = "";
    switch (true) {
        case (!this.employeeVisa.hasOptReceipt):
        msg = "Please upload OPT Receipt";
        break;

        case (this.employeeVisa.hasOptReceipt && !this.employeeVisa.hasUploadedOptEad):
        msg = "Please upload OPT EAD";
        break;

        case (this.employeeVisa.hasUploadedOptEad && this.employeeVisa.isOptEadLessThan100Days && !this.employeeVisa.hasUploadedFormI983):
        msg = "Download and fill I-983";
        warnMsg = "Your OPT is expiring in less than 100 days"
        break;

        case (this.employeeVisa.hasUploadedOptEad && !this.employeeVisa.isOptEadLessThan100Days && this.employeeVisa.hasUploadedFormI983):
        msg = "COMPLETE";
        break;

        case (this.employeeVisa.hasUploadedFormI983 && !this.employeeVisa.hasFormI983HrSignedAndApproved):
        msg = "Waiting for HR to approve and sign I-983";
        break;

        case (this.employeeVisa.hasFormI983HrSignedAndApproved && !this.employeeVisa.hasUploadedFormI20):
        msg = "Please send the I-983 with all necessary documents to your school and upload the new I-20";
        break;

        case (this.employeeVisa.hasFormI983HrSignedAndApproved && this.employeeVisa.hasUploadedFormI20 && !this.employeeVisa.hasUploadedOptStemReceipt):
        msg = "Please upload your OPT STEM Receipt";
        break;

        case (this.employeeVisa.hasUploadedOptStemReceipt && !this.employeeVisa.hasUploadedOptStemEad):
        msg = "Please upload your OPT STEM EAD";
        break;

        default:
        msg = "COMPLETE";
    }

    if (msg === "COMPLETE") {
        this.alertService.info(msg);
    } else {
        this.alertService.warn(msg);
    }

    if (warnMsg) {
      this.alertService.error(warnMsg);
    }
}
  changeBoolStr(bool: boolean): string {
    if (bool)
      return "COMPLETE";
    return "INCOMPLETE"
  }
}
