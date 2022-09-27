import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'vehicleDetailsFilter'
})
export class VehicleDetailsFilterPipe implements PipeTransform{
  public transform(value: any, searchText: any): any {
    if(searchText ==null || value == null){
      return value;
    }

    return value.filter(s => (s.value.toLowerCase()).indexOf(searchText.toLowerCase()) !== -1 || (s.value.toLowerCase()).indexOf(searchText.toLowerCase()) !== -1);
  }
}
