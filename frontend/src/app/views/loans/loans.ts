import { Component } from '@angular/core';
import { LoanRequest } from '../../shared/models/loans/LoanRequest';
import { LoansService } from '../../shared/services/loans/loan.service';
import { APIENDPOINT } from '../../config/configuration';

@Component({
  selector: 'app-loans',
  standalone: false,
  templateUrl: './loans.html',
  styleUrl: './loans.css',
})
export class Loans {
  loanModel: LoanRequest[] = [];
  isEditMode: boolean = false;
  selectedLoan: LoanRequest = {
    id_loan: 0,
    userId: 0,
    bookId: 0,
    loanDate: '',
    returnDate: '',
    returned: false,
  };

  constructor(private readonly LoanService: LoansService) {}

  ngOnInit(): void {
    this.getAllLoan();
  }

  getAllLoan(): void {
    this.LoanService.getList(APIENDPOINT.Loan).subscribe({
      next: (data) => {
        this.loanModel = data;
      },
      error: (error) => {
        console.error('Error fetching Loan:', error);
      },
    });
  }

  deleteLoan(id_loan: string | number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este prestamo?')) {
      this.LoanService.delete(APIENDPOINT.Loan, id_loan).subscribe({
        next: (response) => {
          console.log('Prestamo eliminado exitosamente:', response);
          this.getAllLoan();
        },
        error: (error) => {
          console.error('Error deleting Book:', error);
        },
      });
    }
  }

  openEditModal(loan?: LoanRequest): void {
    this.selectedLoan = loan
      ? { ...loan }
      : {
          id_loan: 0,
          userId: 0,
          bookId: 0,
          loanDate: '',
          returnDate: '',
          returned: false,
        };

    this.isEditMode = !!loan;

    const modalEl = document.getElementById('editModal');
    if (modalEl) {
      new (window as any).bootstrap.Modal(modalEl).show();
    }
  }

  markLoanAsReturned(id: number): void {
    this.LoanService.markAsReturned(APIENDPOINT.Loan, id).subscribe({
      next: () => {
        console.log('Préstamo marcado como devuelto');
        this.getAllLoan();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error al marcar como devuelto:', err),
    });
  }

  saveLoan(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);
    const returned = formData.get('returned') === 'on';

    const isNew = !this.selectedLoan.id_loan || this.selectedLoan.id_loan === 0;

    if (isNew) {
      const loan: Partial<LoanRequest> = {
        userId: Number(formData.get('userId')),
        bookId: Number(formData.get('bookId')),
        loanDate: formData.get('loanDate') as string,
        returnDate: formData.get('returnDate') as string,
        returned,
      };
      this.createLoan(loan);
    } else {
      if (returned && !this.selectedLoan.returned) {
        this.markLoanAsReturned(this.selectedLoan.id_loan);
      } else {
        alert('Solo se puede marcar como devuelto. No se puede revertir.');
      }
    }
  }

  createLoan(loan: Partial<LoanRequest>): void {
    this.LoanService.post(APIENDPOINT.Loan, loan).subscribe({
      next: () => {
        this.getAllLoan();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error creando prestamo:', err),
    });
  }

  updateLoan(loan: LoanRequest): void {
    this.LoanService.put(APIENDPOINT.Loan, loan.id_loan!, loan).subscribe({
      next: () => {
        this.getAllLoan();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error actualizando prestamo:', err),
    });
  }
}
