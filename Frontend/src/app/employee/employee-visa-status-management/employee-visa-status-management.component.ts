import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../common/_services';

@Component({
  selector: 'app-employee-visa-status-management',
  templateUrl: './employee-visa-status-management.component.html',
  styleUrls: ['./employee-visa-status-management.component.css']
})
export class EmployeeVisaStatusManagementComponent implements OnInit {

  hasOptReceipt: boolean;
  hasUploadedOptEad: boolean;
  optEadDaysLeft: number;
  isOptEadLessThan100Days: boolean;
  hasUploadedFormI983: boolean;
  hasFormI983HrSignedAndApproved: boolean;
  hasUploadedFormI20: boolean;
  hasUploadedOptStemReceipt: boolean;
  hasUploadedOptStemEad: boolean;

  documents: string[][];

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
      this.hasOptReceipt = true;
      this.hasUploadedOptEad = false;
      this.optEadDaysLeft = 120;
      this.isOptEadLessThan100Days = this.optEadDaysLeft < 100;
      this.hasUploadedFormI983 = false;
      this.hasFormI983HrSignedAndApproved = false;
      this.hasUploadedFormI20 = false;
      this.hasUploadedOptStemReceipt = false;
      this.hasUploadedOptStemEad = false;

      this.documents = [
        ["OPT Receipt", this.changeBoolStr(this.hasOptReceipt)],
        ["OPT EAD", this.changeBoolStr(this.hasUploadedOptEad)],
        ["OPT EAD less than 100 days", this.changeBoolStr(this.isOptEadLessThan100Days)],
        ["Form I-983", this.changeBoolStr(this.hasUploadedFormI983)],
        ["Form I-20", this.changeBoolStr(this.hasUploadedFormI20)],
        ["OPT STEM Receipt", this.changeBoolStr(this.hasUploadedOptStemReceipt)],
        ["OPT STEM EAD", this.changeBoolStr(this.hasUploadedOptStemEad)],
      ]

      this.showCurrentStatusNotification();

  }

  showCurrentStatusNotification() {
      let msg: string = "";
      switch (true) {
          case (this.hasOptReceipt && !this.hasUploadedOptEad):
          msg = "Please upload OPT EAD";
          break;

          case (this.hasUploadedOptEad && this.isOptEadLessThan100Days && !this.hasUploadedFormI983):
          msg = "Download and fill I-983";
          break;

          case (this.hasUploadedFormI983 && !this.hasFormI983HrSignedAndApproved):
          msg = "Waiting for HR to approve and sign I-983";
          break;

          case (this.hasFormI983HrSignedAndApproved && !this.hasUploadedFormI20):
          msg = "Please send the I-983 with all necessary documents to your school and upload the new I-20";
          break;

          case (this.hasFormI983HrSignedAndApproved && this.hasUploadedFormI20 && !this.hasUploadedOptStemReceipt):
          msg = "Please upload your OPT STEM Receipt";
          break;

          case (this.hasUploadedOptStemReceipt && !this.hasUploadedOptStemEad):
          msg = "Please upload your OPT STEM EAD";
          break;

          default:
          msg = "COMPLETE";
      }

      if (msg === "COMPLETE") {
          this.alertService.info(msg);
      } else {
          this.alertService.success(msg);
      }
  }

  changeBoolStr(bool: boolean): string {
    if (bool)
      return "COMPLETE";
    return "INCOMPLETE"
  }

}
