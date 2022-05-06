import { ICartItem } from "./ICartItem";

export interface ICartItems {
  cartItems: ICartItem[];
  totalCost: number;
}
