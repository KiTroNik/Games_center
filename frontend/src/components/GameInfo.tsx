import React, { useState } from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { toast } from "react-toastify";
import { Card, CardContent, TextField } from "@mui/material";
import { IGame } from "../api/interfaces/IGame";
import * as yup from "yup";
import useApi from "../api/useApi";
import { useFormik } from "formik";

const ADD_TO_CART_URL = "/api/cart";

const GameInfo = ({ id, name, description, price, imageURL }: IGame) => {
  const api = useApi();

  const validationSchema = yup.object({
    quantity: yup
      .number()
      .required("Quantity is required.")
      .min(1, "You can't add less than 1 product."),
  });

  const formik = useFormik({
    initialValues: {
      quantity: 1,
    },
    validationSchema: validationSchema,
    onSubmit: async (values, { setErrors }) => {
      try {
        await api.post(
          ADD_TO_CART_URL,
          JSON.stringify({
            productId: id,
            quantity: values.quantity,
          })
        );
        toast.success("Successfully added to cart :)", {
          position: toast.POSITION.TOP_CENTER,
        });
      } catch (err) {
        toast.error("This product already exists in cart.", {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    },
  });

  return (
    <Container sx={{ width: 600 }}>
      <Typography
        variant="h3"
        component="h1"
        marginTop={3}
        sx={{ justifyContent: "center", textAlign: "center" }}
      >
        {name}
      </Typography>
      <Box marginTop={3} sx={{ display: "flex", justifyContent: "center" }}>
        <img src={imageURL} height={425} alt="" />
      </Box>
      <Typography
        variant="h4"
        component="h4"
        marginTop={3}
        marginBottom={3}
        sx={{ justifyContent: "center", textAlign: "center" }}
      >
        Game and Legal Info
      </Typography>
      <Card variant="outlined">
        <CardContent>
          <Box sx={{ display: "flex" }}>
            <Typography component="p" marginY={3} textAlign={"justify"}>
              {description}
            </Typography>
          </Box>
        </CardContent>
      </Card>

      <form onSubmit={formik.handleSubmit}>
        <Box sx={{ display: "flex", justifyContent: "center" }} marginTop={4}>
          <TextField
            id="quantity"
            label="Quantity"
            type="number"
            value={formik.values.quantity}
            onChange={formik.handleChange}
            error={formik.touched.quantity && Boolean(formik.errors.quantity)}
            helperText={formik.touched.quantity && formik.errors.quantity}
            InputLabelProps={{
              shrink: true,
            }}
            sx={{ paddingRight: 5 }}
          />
          <Button
            variant="contained"
            type="submit"
          >{`Add to cart ${price}$`}</Button>
        </Box>
      </form>
    </Container>
  );
};

export default GameInfo;
