export interface Pageable {
    pageNumber: number;
    pageSize: number;
    property?: string;
    direction?: string;
}