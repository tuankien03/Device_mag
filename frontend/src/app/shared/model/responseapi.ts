export interface ResponseApi<T> {
    status: number;
    message: string;
    body: T;
}