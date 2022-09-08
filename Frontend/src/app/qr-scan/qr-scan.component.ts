import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ZXingScannerComponent} from "@zxing/ngx-scanner";
import {QrScanService} from "./qr-scan.service";

@Component({
  selector: 'app-qr-scan',
  templateUrl: './qr-scan.component.html',
  styleUrls: ['./qr-scan.component.css']
})
export class QrScanComponent implements OnInit, AfterViewInit {
  scannerEnabled: boolean = false;

  @ViewChild('scanner') scanner: ZXingScannerComponent;
  hasDevices: boolean;
  hasPermission: boolean;
  qrResultString: string;
  qrResult;

  availableDevices: MediaDeviceInfo[];
  currentDevice: MediaDeviceInfo = null;

  constructor(private qrScanS: QrScanService) {
    // setTimeout(()=>{
    //   console.log(this.scanner)
    // },1000)
  }

  ngAfterViewInit() {
    // console.log(this.scanner)
    this.scanner.camerasFound.subscribe((devices: MediaDeviceInfo[]) => {
      this.hasDevices = true;
      this.availableDevices = devices;
      console.log(this.availableDevices)
      this.currentDevice = devices[0]
      // selects the devices's back camera by default
      // for (const device of devices) {
      //     if (/back|rear|environment/gi.test(device.label)) {
      //         this.scanner.changeDevice(device);
      //         this.currentDevice = device;
      //         break;
      //     }
      // }
    });

    this.scanner.camerasNotFound.subscribe(() => this.hasDevices = false);
    this.scanner.scanComplete.subscribe((result) => this.qrResult = result);
    this.scanner.permissionResponse.subscribe((perm: boolean) => this.hasPermission = perm);
  }

  // onDeviceSelectChange(selectedValue: string) {
  //   console.debug('Selection changed: ', selectedValue);
  //   this.currentDevice = this.scanner.device;
  // }

  ngOnInit(): void {

  }

  setOutput(output) {
    console.log(output)
  }

  public scanSuccessHandler($event: any) {
    // console.log($event)
    this.qrScanS.qrValue.next($event)
  }

  public enableScanner() {
    this.scannerEnabled = !this.scannerEnabled;
    // this.information = "No se ha detectado información de ningún código. Acerque un código QR para escanear.";
  }
}
