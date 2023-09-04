import { Component, Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-display-response',
  templateUrl: './display-response.component.html',
  styleUrls: ['./display-response.component.scss']
})
export class DisplayResponseComponent {
  constructor(
    public dialogRef: MatDialogRef<DisplayResponseComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
