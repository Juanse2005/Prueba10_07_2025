import { Component, OnInit } from '@angular/core';
import { LoanStatsService } from '../../shared/services/loans/loanstats.service';
import { UserService } from '../../shared/services/users/user.service';
import { APIENDPOINT } from '../../config/configuration';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  usuariosConPrestamo: number = 0;
  usuariosSinPrestamo: number = 0;
  porcentajeConPrestamo: number = 0;

  constructor(
    private loanStatsService: LoanStatsService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.cargarEstadistica();
  }

  cargarEstadistica(): void {
    this.userService.getList(APIENDPOINT.Users).subscribe((usuarios) => {
      const totalUsuarios = usuarios.length;

      this.loanStatsService.getUsersWithLoans().subscribe((stat) => {
        this.usuariosConPrestamo = stat.total;
        this.usuariosSinPrestamo = totalUsuarios - stat.total;
        this.porcentajeConPrestamo = (stat.total / totalUsuarios) * 100;
      });
    });
  }
}
