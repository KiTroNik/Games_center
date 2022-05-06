import { IOrderItem } from "./IOrderItem";

export interface IOrders {
  id: number;
  createdDate: string;
  totalPrice: number;
  orderItems: IOrderItem[];
  phoneNumber: string;
  street: string;
  postalCode: string;
  city: string;
  status: string;
}
