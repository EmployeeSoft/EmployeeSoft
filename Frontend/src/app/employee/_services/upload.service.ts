import { Injectable } from '@angular/core';
import * as s3 from 'aws-sdk/clients/s3';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  public docFile: any;
  public bucket: any;

  constructor() {
  }

  // tslint:disable-next-line:typedef
  uploadFile(file: any) {
    const contentType = file.type;
    const bucket = new s3(
      {
        accessKeyId: 'AKIASULWYEBDF43WV4CW',
        secretAccessKey: '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',
        region: 'us-west-1'
      }
    );
    const params = {
      Bucket: 'employeefilebucket',
      Key: file.name,
      Body: file,
      ACL: 'public-read',
      ContentType: contentType
    };
    // tslint:disable-next-line:only-arrow-functions typedef
    bucket.upload(params, function(err: any, data: any) {
      if (err) {
        console.log('There was an error uploading your file: ', err);
        return false;
      }
      console.log('Successfully uploaded file.', data);
      return true;
    });
  }
}
