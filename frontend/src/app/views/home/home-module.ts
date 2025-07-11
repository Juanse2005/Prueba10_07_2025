import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing-module';
import { Home } from './home';
import { NgApexchartsModule } from 'ng-apexcharts';

@NgModule({
  declarations: [Home],
  imports: [CommonModule, HomeRoutingModule,NgApexchartsModule],
})
export class HomeModule {}
