import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AccountService, AlertService } from '../../../common/_services';
import { HireService } from '../../_services/hire.service';

@Component({
  selector: 'app-register-new-hire',
  templateUrl: './register-new-hire.component.html',
  styleUrls: ['./register-new-hire.component.css']
})
export class RegisterNewHireComponent implements OnInit {

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private hireService: HireService,
      private alertService: AlertService
  ) { }

  ngOnInit() {
      this.form = this.formBuilder.group({
          email: ['', [Validators.required, Validators.email]]
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
      this.submitted = true;

      // reset alerts on submit
      this.alertService.clear();

      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }

      this.loading = true;
      this.hireService.sendRegistrationToken(this.f.email.value)
          .pipe(first())
          .subscribe({
              next: (data) => {
                  console.log(data);    // data we received from the backend

                  this.alertService.success(`Registration Token sent to: ` + this.f.email.value);
              },
              error: error => {
                  this.alertService.error(error);
                  this.loading = false;
              }
          });
  }
}
