export interface IApkCalculation {
  idApk: number;
  apkOdpowiedz: boolean;
}

export interface IStepInsurancePeriod {
  dataPoczatkuOchrony: Date;
  dataKoncaOchrony: Date;
  apk: IApkCalculation[];
}
