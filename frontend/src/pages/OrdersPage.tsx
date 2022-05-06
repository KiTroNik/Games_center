import React from "react";
import useApi from "../api/useApi";
import { useQuery, UseQueryResult } from "react-query";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import { IOrders } from "../api/interfaces/IOrders";
import Order from "../components/Order";

const ORDERS_URL = "api/orders";

const OrdersPage = () => {
  const api = useApi();

  const fetchOrders = async (): Promise<IOrders[]> => {
    const response = await api.get(ORDERS_URL);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IOrders[], Error> =
    useQuery<IOrders[], Error>("orders", fetchOrders);

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
          Your Orders:
        </Typography>
        <hr />
        <Grid container spacing={5} marginTop={5}>
          {data?.map((item, id) => (
            <Order key={id} item={item} />
          ))}
        </Grid>
      </Container>
    </>
  );
};

export default OrdersPage;
