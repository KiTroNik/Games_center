import React, { useState } from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import { IOrders } from "../api/interfaces/IOrders";
import { Paper } from "@mui/material";

type Props = {
  item: IOrders;
};

const Order = ({ item }: Props) => {
  return (
    <Container sx={{ width: 600, marginTop: 5 }}>
      <Paper elevation={6}>
        {item?.orderItems?.map((item, id) => (
          <Typography
            variant="h5"
            component="h3"
            marginTop={3}
            sx={{ justifyContent: "center", textAlign: "center" }}
          >
            {item.product.name} quantity: {item.quantity}
          </Typography>
        ))}

        <Typography
          variant="h5"
          component="h3"
          marginTop={3}
          sx={{ justifyContent: "center", textAlign: "center" }}
        >
          Total price: ${item.totalPrice}
        </Typography>
        <Typography
          variant="h5"
          component="h3"
          marginTop={3}
          sx={{
            justifyContent: "center",
            textAlign: "center",
            marginBottom: 5,
          }}
        >
          status: {item.status}
        </Typography>
      </Paper>
    </Container>
  );
};

export default Order;
