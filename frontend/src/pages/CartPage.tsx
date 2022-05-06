import React, { useState } from "react";
import useApi from "../api/useApi";
import { ICartItems } from "../api/interfaces/ICartItems";
import { useQuery, UseQueryResult } from "react-query";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import CartItem from "../components/CartItem";

const CART_URL = "api/cart";

const CartPage = () => {
  const [updated, setUpdated] = useState(0);
  const api = useApi();

  const fetchCart = async (): Promise<ICartItems> => {
    const response = await api.get(CART_URL);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<ICartItems, Error> =
    useQuery<ICartItems, Error>(["cart", updated], fetchCart);

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
          Your Cart:
        </Typography>
        <hr />
        <Grid container spacing={5}>
          {data?.cartItems?.map((item, id) => (
            <CartItem
              key={id}
              item={item}
              setUpdated={setUpdated}
              updated={updated}
            />
          ))}
        </Grid>
      </Container>
      <Typography
        variant="h2"
        component="h2"
        marginBottom={3}
        marginTop={5}
        sx={{ display: "flex", justifyContent: "center" }}
      >
        Total ${data?.totalCost}
      </Typography>
      {data?.totalCost && data.totalCost > 0 && (
        <Box
          sx={{ display: "flex", justifyContent: "center", marginBottom: 5 }}
          marginTop={4}
        >
          <Button
            variant="contained"
            color="success"
            href={"/place_order"}
          >{`Place an order`}</Button>
        </Box>
      )}
    </>
  );
};

export default CartPage;
