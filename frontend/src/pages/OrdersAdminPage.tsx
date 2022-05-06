import React, { useState } from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import Button from "@mui/material/Button";
import useApi from "../api/useApi";
import { useMutation, useQuery, UseQueryResult } from "react-query";
import { useNavigate } from "react-router-dom";
import { IOrder } from "../api/interfaces/IOrder";

const LIST_ORDERS_URL = "api/orders/all";

const OrdersAdminPage = () => {
  const api = useApi();
  const navigate = useNavigate();

  const fetchOrders = async (): Promise<IOrder[]> => {
    const response = await api.get(LIST_ORDERS_URL);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IOrder[], Error> =
    useQuery<IOrder[], Error>("all_orders", fetchOrders);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error</div>;
  }

  return (
    <>
      <Container>
        <Typography
          variant="h2"
          component="h2"
          marginBottom={3}
          marginTop={5}
          sx={{ display: "flex", justifyContent: "center", marginBottom: 5 }}
        >
          Admin Panel
        </Typography>
        <hr />
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Date</TableCell>
                <TableCell align="right">Price</TableCell>
                <TableCell align="right">status</TableCell>
                <TableCell align="right">Update</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data?.map((order) => (
                <TableRow
                  key={order.id}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {order.createdDate}
                  </TableCell>
                  <TableCell align="right">${order.totalPrice}</TableCell>
                  <TableCell align="right">{order.status}</TableCell>
                  <TableCell align="right">
                    <Button
                      variant="contained"
                      onClick={() => {
                        navigate(`/admin/orders/${order.id}`);
                      }}
                    >{`Update status`}</Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    </>
  );
};

export default OrdersAdminPage;
