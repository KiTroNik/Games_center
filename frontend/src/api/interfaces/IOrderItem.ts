import { IGame } from "./IGame";

export interface IOrderItem {
  id: number;
  quantity: number;
  price: number;
  createdDate: string;
  product: IGame;
}
