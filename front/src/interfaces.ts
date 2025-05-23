export interface UpdateBookRequest {
  id: string;
  title: string;
  pageCount: number;
  version: number;
}

export interface CreateBookRequest {
  title: string;
  pageCount: number;
}
