import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../common/service/auth.service';

@Component({
    selector: 'my-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    public login: string;
    public password: string;
    public hasError: boolean;

    constructor(private authService: AuthService, private router: Router) {
        this.hasError = false;
    }

    ngOnInit(): void {
    }

    signIn(): void {
        this.authService.signIn(this.login, this.password)
            .then(user => {
                if (user != null) {
                    this.router.navigate(['/home']);
                }
            })
            .catch(p => {
                this.hasError = true;
            });
    }
}
