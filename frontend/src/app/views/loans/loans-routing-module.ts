import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Loans } from './loans';

const routes: Routes = [{ path: '', component: Loans }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoansRoutingModule { }
