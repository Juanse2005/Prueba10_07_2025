import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/models/users/user';
import { UserService } from '../../shared/services/users/user.service';
import { APIENDPOINT } from '../../config/configuration';

@Component({
  selector: 'app-users',
  templateUrl: './users.html',
  standalone: false,
  styleUrl: './users.css',
})
export class UsersComponent implements OnInit {
  usersModel: User[] = [];
  isEditMode: boolean = false;
  selectedUser: User = {
    id_user: 0,
    name: '',
    lastName: '',
    email: '',
    password: '',
  };

  constructor(private readonly userService: UserService) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.userService.getList(APIENDPOINT.Users).subscribe({
      next: (data) => {
        this.usersModel = data;
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      },
    });
  }

  deleteUser(userId: string | number): void {
    if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
      this.userService.delete(APIENDPOINT.Users, userId).subscribe({
        next: (response) => {
          console.log('Usuario eliminado exitosamente:', response);
          this.getAllUsers();
        },
        error: (error) => {
          console.error('Error deleting user:', error);
        },
      });
    }
  }

  openEditModal(user?: User): void {
    this.isEditMode = !!user;
    this.selectedUser = user
      ? { ...user }
      : { id_user: 0, name: '', lastName: '', email: '', password: '' };

    const modalEl = document.getElementById('editModal');
    if (modalEl) {
      new (window as any).bootstrap.Modal(modalEl).show();
    }
  }
  saveUser(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    const user: Partial<User> = {
      name: formData.get('name') as string,
      lastName: formData.get('lastName') as string,
      email: formData.get('email') as string,
      password: formData.get('password') as string,
    };

    const isNew = !this.selectedUser.id_user || this.selectedUser.id_user === 0;

    if (isNew) {
      this.createUser(user);
    } else {
      this.updateUser({ ...user, id_user: this.selectedUser.id_user } as User);
    }
  }

  createUser(user: Partial<User>): void {
    this.userService.post(APIENDPOINT.Users, user).subscribe({
      next: () => {
        this.getAllUsers();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error creando usuario:', err),
    });
  }

  updateUser(user: User): void {
    this.userService.put(APIENDPOINT.Users, user.id_user!, user).subscribe({
      next: () => {
        this.getAllUsers();
        (window as any).bootstrap.Modal.getInstance(
          document.getElementById('editModal')
        )?.hide();
      },
      error: (err) => console.error('Error actualizando usuario:', err),
    });
  }
}
