import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AccountService, AlertService } from '../_services';
import { HttpClient } from '@angular/common/http';

@Component({ templateUrl: 'register.component.html' })
export class RegisterComponent implements OnInit {
    form: FormGroup;
    loading = false;
    submitted = false;
    token: string | null;
    data: any;
    email: string;
    id: number;
    userId: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private accountService: AccountService,
        private alertService: AlertService,
        private http: HttpClient
    ) { }

    ngOnInit() {
        this.token = this.route.snapshot.queryParamMap.get('token');
        console.log(this.token);
        this.http.get<any>(`http://localhost:9999/register/` + this.token).subscribe(data => {
            this.data = data;
            console.log(data);
            console.log(data.registrationTokenDomain.email);
            this.email = data.registrationTokenDomain.email;
            console.log(data.serviceStatus.statusCode);
        });
        this.form = this.formBuilder.group({
            email: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]]
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
      this.accountService.registerNewHire(this.f.email.value, this.f.username.value, this.f.password.value)
          .pipe(first())
          .subscribe({
              next: (x) => {
                  console.log(x);
                  this.id = x.userId;
                  console.log(this.id);
                  this.userId = this.id.toString();
                  this.alertService.success('Registration successful', { keepAfterRouteChange: true });
                  this.router.navigateByUrl('/onboard?email=' + this.email + '&userId=' + this.userId);
              },
              error: error => {
                  this.alertService.error(error);
                  this.loading = false;
              }
          });
      }
}
