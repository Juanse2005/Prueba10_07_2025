
export interface LoanRequest {
  id_loan: number;
  userId: number;
  bookId: number;
  loanDate: string;
  returnDate: string;
  returned: boolean;
}
