import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./models/User";
import {environment as env} from "../environments/environment";
import {map} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {Quack} from "./models/Quack";
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class QuackRService {

    constructor(protected httpClient: HttpClient, protected authService: AuthenticationService) {
        this.httpClient = httpClient;
    }

    getQuack(id: number): Observable<Quack> {
        return this.httpClient.get<any[]>(`${env.apiUrl}/quacks/${id}`, {headers: this.authService.getAuthHeaders()}).pipe(
            map(obj => Quack.fromObject(obj))
        );
    }

    getAllQuacks(): Observable<Quack[]> {
        return this.httpClient.get<any[]>(`${env.apiUrl}/quacks`, {headers: this.authService.getAuthHeaders()}).pipe(
            map(objArr => objArr.map(obj => Quack.fromObject(obj)))
        );
    }

    createQuack(title: string, text: string): Observable<Quack> {
        return this.httpClient.post<any>(`${env.apiUrl}/quacks`, {title, text}, {headers: new HttpHeaders({'Content-Type':'application/json'})}).pipe(
            map(obj => Quack.fromObject(obj))
        );
    }

    getUser(id: number): Observable<User> {
        return this.httpClient.get<any[]>(`${env.apiUrl}/users/${id}`, {headers: this.authService.getAuthHeaders()}).pipe(
            map(obj => User.fromObject(obj))
        );
    }

    getAllUsers(): Observable<User[]> {
        return this.httpClient.get<any[]>(`${env.apiUrl}/users`, {headers: this.authService.getAuthHeaders()}).pipe(
            map(objArr => objArr.map(obj => User.fromObject(obj)))
        );
    }

    createUser(username: string, password: string, email: string): Observable<User> {
        return this.httpClient.post<any>(`${env.apiUrl}/users`, {username, password, email}, {headers: new HttpHeaders({'Content-Type':'application/json'})}).pipe(
            map(obj => User.fromObject(obj))
        );
    }


}
