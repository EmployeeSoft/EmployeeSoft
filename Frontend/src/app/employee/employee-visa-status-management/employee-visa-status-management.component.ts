import { Component, OnInit } from '@angular/core';
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

  employeeVisa: EmployeeVisa;

  constructor(private alertService: AlertService, private employeeVisaService: EmployeeVisaService) { }

  ngOnInit(): void {
    console.log(this.employeeVisa);

    setTimeout(
      ()=> {this.employeeVisaService.getById()
        .subscribe(data=>{
            console.log(data);
            this.employeeVisa = data;
            this.showCurrentStatusNotification();
            this.documents = [
              ["OPT Receipt", this.changeBoolStr(this.employeeVisa.hasOptReceipt), "opt_receipt.pdf"],
              ["OPT EAD", this.changeBoolStr(this.employeeVisa.hasUploadedOptEad), "opt_ead.pdf"],
              ["Form I-983 submitted to HR", this.changeBoolStr(this.employeeVisa.hasUploadedFormI983), "emp_signed_i983.pdf"],
              ["Form I-983 signed and approved by HR", this.changeBoolStr(this.employeeVisa.hasFormI983HrSignedAndApproved), "hr_signed_i983.pdf"],
              ["Form I-20", this.changeBoolStr(this.employeeVisa.hasUploadedFormI20), "most_recent_i20.pdf"],
              ["OPT STEM Receipt", this.changeBoolStr(this.employeeVisa.hasUploadedOptStemReceipt), "opt_stem_receipt.pdf"],
              ["OPT STEM EAD", this.changeBoolStr(this.employeeVisa.hasUploadedOptStemEad), "opt_stem_ead.pdf"],
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
          }, error=> console.log(error));}
      ,1000);

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
