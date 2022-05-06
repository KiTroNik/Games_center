import { IGame } from "./IGame";

export interface ICartItem {
  id: number;
  quantity: number;
  product: IGame;
}
