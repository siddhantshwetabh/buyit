import { Component, Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';


@Component({
  selector: 'app-confimation-pop-up',
  templateUrl: './confimation-pop-up.component.html',
  styleUrls: ['./confimation-pop-up.component.scss']
})
export class ConfimationPopUpComponent {

  constructor(
    public dialogRef: MatDialogRef<ConfimationPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {}

  onCloseClick() {
    this.dialogRef.close("Cancel");
  }

  onConfirmClick(){
    this.dialogRef.close("Confirm");
  }

}


