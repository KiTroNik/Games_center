import { IGame } from "./IGame";

interface ISort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

interface IPageable {
  sort: ISort;
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  unpaged: boolean;
}

export interface IGameList {
  content: IGame[];
  pageable: IPageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: ISort;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}
