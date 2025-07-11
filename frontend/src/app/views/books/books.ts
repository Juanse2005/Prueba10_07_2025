import { Component } from '@angular/core';
import { BooksService } from '../../shared/services/books/books.service';
import { APIENDPOINT } from '../../config/configuration';
import { Book } from '../../shared/models/books/book';

@Component({
  selector: 'app-books',
  standalone: false,
  templateUrl: './books.html',
  styleUrl: './books.css',
})
export class BooksComponent {
  bookModel: Book[] = [];
  isEditMode: boolean = false;
  selectedBook: Book = {
    id: 0,
    title: '',
    writer: '',
    gender: '',
  };

  constructor(private readonly booksService: BooksService) {}

  ngOnInit(): void {
    this.getAllBooks();
  }

  getAllBooks(): void {
    this.booksService.getList(APIENDPOINT.Books).subscribe({
      next: (data) => {
        this.bookModel = data;
        
      },
      error: (error) => {
        console.error('Error fetching books:', error);
      },
    });
  }

  deleteBook(id: string | number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este libro?')) {
      this.booksService.delete(APIENDPOINT.Books, id).subscribe({
        next: (response) => {
          console.log('Libro eliminado exitosamente:', response);
          this.getAllBooks();
        },
        error: (error) => {
          console.error('Error deleting Book:', error);
        },
      });
    }
  }


  openEditModal(book?: Book): void {
    this.selectedBook = book
      ? { ...book }
      : { id: 0, title: '', writer: '', gender: '' };

    this.isEditMode = !!book;

    const modalEl = document.getElementById('editModal');
    if (modalEl) {
      new (window as any).bootstrap.Modal(modalEl).show();
    }
  }

  saveBook(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    const book: Partial<Book> = {
      title: formData.get('title') as string,
      writer: formData.get('writer') as string,
      gender: formData.get('gender') as string,
    };

    const isNew = !this.selectedBook.id || this.selectedBook.id === 0;

    if (isNew) {
      this.createBook(book);
    } else {
      this.updateBook({ ...book, id: this.selectedBook.id } as Book);
    }
  }

  createBook(Book: Partial<Book>): void {
    this.booksService.post(APIENDPOINT.Books, Book).subscribe({
      next: () => {
        this.getAllBooks();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error creando libro:', err),
    });
  }

  updateBook(book: Book): void {
    this.booksService.put(APIENDPOINT.Books, book.id!, book).subscribe({
      next: () => {
        this.getAllBooks();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error actualizando libro:', err),
    });
  }
}
