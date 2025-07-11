import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Layout } from './shared/components/layout/layout';

//Rutas con LazyLoading para mejor rendimiento y carga de modulos
const routes: Routes = [
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
      },
      {
        path: 'users',
        loadChildren: () =>
          import('./views/users/users-module').then((m) => m.UsersModule),
      },
      {
        path: 'books',
        loadChildren: () =>
          import('./views/books/books-module').then((m) => m.BooksModule),
      },
      {
        path: 'loans',
        loadChildren: () =>
          import('./views/loans/loans-module').then((m) => m.LoansModule),
      },
      {
        path: 'home',
        loadChildren: () =>
          import('./views/home/home-module').then((m) => m.HomeModule),
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'home',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
