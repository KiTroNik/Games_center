import React, { useState } from "react";
import { ICartItem } from "../api/interfaces/ICartItem";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import { Card, CardContent, TextField } from "@mui/material";
import Button from "@mui/material/Button";
import useApi from "../api/useApi";
import * as yup from "yup";
import { useFormik } from "formik";
import { toast } from "react-toastify";

type Props = {
  item: ICartItem;
  setUpdated: React.Dispatch<React.SetStateAction<number>>;
  updated: number;
};

const CartItem = ({ item, setUpdated, updated }: Props) => {
  const [quantity, setQuantity] = useState(item.quantity);
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
        await api.patch(
          `api/cart/${item.id}`,
          JSON.stringify({
            quantity: values.quantity,
          })
        );
        toast.success("Successfully updated quantity :)", {
          position: toast.POSITION.TOP_CENTER,
        });
        setQuantity(values.quantity);
        setUpdated(updated + 1);
      } catch (err) {
        toast.error("Sorry something went wrong.", {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    },
  });

  const deleteItemFromCart = async () => {
    await api.delete(`api/cart/${item.id}`);
    setUpdated(updated + 1);
  };

  return (
    <Container sx={{ width: 600, marginTop: 5 }}>
      <Typography
        variant="h3"
        component="h1"
        marginTop={3}
        sx={{ justifyContent: "center", textAlign: "center" }}
      >
        {item.product.name}
      </Typography>
      <Typography
        variant="h4"
        component="h4"
        marginTop={3}
        marginBottom={3}
        sx={{ justifyContent: "center", textAlign: "center" }}
      >
        Quantity: {quantity}, Total Price: ${quantity * item.product.price}
      </Typography>
      <form onSubmit={formik.handleSubmit}>
        <Box
          sx={{ display: "flex", justifyContent: "center", marginBottom: 5 }}
          marginTop={4}
        >
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
          <Button variant="contained" type="submit">{`Update quantity`}</Button>
          <Button
            variant="contained"
            sx={{ marginLeft: 5 }}
            color="error"
            onClick={deleteItemFromCart}
          >{`Delete item`}</Button>
        </Box>
      </form>
      <hr />
    </Container>
  );
};

export default CartItem;
