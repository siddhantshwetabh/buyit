import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiCallService } from 'src/app/services/api-call.service';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DisplayResponseComponent } from '../display-response/display-response.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  newUserForm: FormGroup;
  existingUserForm: FormGroup;
  isSignUpScreenVisible = true;
  screenVisible = "sing-up";
  adminUserForm: FormGroup;

  data = {
    "userFirstName": "",
    "userLastName": "",
    "userPassword": "",
    "phNo": "",
    "emailId": ""
  }

  constructor(private formBuilder: FormBuilder, private router: Router, private apiCallService: ApiCallService,
    public dialog: MatDialog) {
    this.newUserForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10),Validators.pattern('^[a-zA-Z]+$')]],
      lastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10),Validators.pattern('^[a-zA-Z]+$')]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(10), Validators.maxLength(10)]],
      email: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(18)]]
    });

    this.existingUserForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(18)]]
    })

    this.adminUserForm = this.formBuilder.group({
      adminEmail: ['', Validators.required],
      adminPassword: ['', Validators.required]
    })
  }

  ngOnInit(): void {
  }

  showLoginScreen() {
    this.screenVisible = 'login'
  }

  showsignUpScreen() {
    this.screenVisible = "sing-up";
  }

  showAdminLogin() {
    this.screenVisible = "admin-login";
  }

  loginButtonClicked() {
    if (this.existingUserForm.valid) {
      const emailID = this.existingUserForm.controls['email'].value;
      const password = this.existingUserForm.controls['password'].value;
      this.apiCallService.loginExistingUser(emailID, password).subscribe((res: any) => {

        sessionStorage.setItem('currentUser', JSON.stringify({ user: res }));
        let auth;
        if (sessionStorage.getItem('currentUser')) {
          auth = sessionStorage.getItem('currentUser');
        }
        window.location.href='home'
      },
        (error: any) => {
          this.openDialog(error.error.message)
        })
    }
    else{
      this.existingUserForm.markAllAsTouched();
    }
  }

  signUpButtonClicked() {
    if (this.newUserForm.valid) {
      this.data.userFirstName = this.newUserForm.controls['firstName'].value;
      this.data.userLastName = this.newUserForm.controls['lastName'].value;
      this.data.phNo = this.newUserForm.controls['phoneNumber'].value;
      this.data.emailId = this.newUserForm.controls['email'].value;
      this.data.userPassword = this.newUserForm.controls['password'].value;
      this.apiCallService.registerNewUser(this.data).subscribe((res) => {
        this.openDialog("Sign Up successful, Please Login to continue.")
        this.showLoginScreen()
      },
        (error: any) => {
          this.openDialog(error.error.message)
        }
      )
    }
    else{
      this.newUserForm.markAllAsTouched();
    }
  }

  adminLoginClicked() {
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(DisplayResponseComponent, {
      width: '650px',
      data: data,
    });
    dialogRef.afterClosed().subscribe(result => {
      this.newUserForm.reset();
      this.existingUserForm.reset();
      this.adminUserForm.reset();
    });
  }
}
