import * as React from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useFormik } from "formik";
import * as yup from "yup";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import useApi from "../api/useApi";

const theme = createTheme();

export default function PlaceOrderPage() {
  const navigate = useNavigate();
  const api = useApi();

  const validationSchema = yup.object({
    phone: yup
      .string()
      .required("Phone Number is required.")
      .matches(
        /^(?:(?:(?:\+|00)?48)|(?:\(\+?48\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-8]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\d{7}$/,
        "Must be valid polish number"
      ),
    street: yup
      .string()
      .min(3, "Street can't be less than 3")
      .required("Street is required."),
    postal: yup
      .string()
      .required("Postal code is required")
      .matches(/^[0-9]{2}-[0-9]{3}$/, "Must be valid polish postal code."),
    city: yup.string().required("City is required"),
  });

  const formik = useFormik({
    initialValues: {
      phone: "",
      street: "",
      postal: "",
      city: "",
    },
    validationSchema: validationSchema,
    onSubmit: async (values, { setErrors }) => {
      try {
        await api.post(
          "/api/orders",
          JSON.stringify({
            phoneNumber: values.phone,
            street: values.street,
            postalCode: values.postal,
            city: values.city,
          })
        );
        toast.success("Thanks for your purchases :)", {
          position: toast.POSITION.TOP_CENTER,
        });
        navigate("/orders");
      } catch (err) {
        toast.error("Sorry something went wrong", {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Typography component="h1" variant="h5">
            Place order
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={formik.handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="phone"
                  label="Phone Number"
                  name="phone"
                  autoComplete="Phone Number"
                  value={formik.values.phone}
                  onChange={formik.handleChange}
                  error={formik.touched.phone && Boolean(formik.errors.phone)}
                  helperText={formik.touched.phone && formik.errors.phone}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="street"
                  label="Street"
                  name="street"
                  autoComplete="street"
                  value={formik.values.street}
                  onChange={formik.handleChange}
                  error={formik.touched.street && Boolean(formik.errors.street)}
                  helperText={formik.touched.street && formik.errors.street}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="postal"
                  label="Postal Code"
                  name="postal"
                  autoComplete="postal"
                  value={formik.values.postal}
                  onChange={formik.handleChange}
                  error={formik.touched.postal && Boolean(formik.errors.postal)}
                  helperText={formik.touched.postal && formik.errors.postal}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  name="city"
                  label="City"
                  id="city"
                  autoComplete="city"
                  value={formik.values.city}
                  onChange={formik.handleChange}
                  error={formik.touched.city && Boolean(formik.errors.city)}
                  helperText={formik.touched.city && formik.errors.city}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Place an order
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
