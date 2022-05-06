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
import { useNavigate, useParams } from "react-router-dom";
import useApi from "../api/useApi";
import { IGame } from "../api/interfaces/IGame";
import { useQuery, UseQueryResult } from "react-query";
import { IOrder } from "../api/interfaces/IOrder";

const theme = createTheme();

export default function UpdateOrderPage() {
  const navigate = useNavigate();
  const api = useApi();
  const { id } = useParams();

  const fetchOrder = async (): Promise<IOrder> => {
    const response = await api.get(`/api/orders/${id}`);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IOrder, Error> = useQuery<
    IOrder,
    Error
  >("order", fetchOrder);

  const validationSchema = yup.object({
    status: yup.string().required("Status is required."),
  });

  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      status: data?.status,
    },
    validationSchema: validationSchema,
    onSubmit: async (values, { setErrors }) => {
      try {
        await api.patch(
          `/api/orders/${data?.id}`,
          JSON.stringify({
            status: values.status,
          })
        );
        toast.success("Status updated :)", {
          position: toast.POSITION.TOP_CENTER,
        });
        navigate("/admin/orders");
      } catch (err) {
        toast.error("Sorry something went wrong", {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    },
  });

  if (isLoading) {
    return <Box>Loading...</Box>;
  }

  if (isError) {
    return <Box>Error</Box>;
  }

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
            Update status
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
                  id="status"
                  label="status"
                  name="status"
                  autoComplete="status"
                  value={formik.values.status}
                  onChange={formik.handleChange}
                  error={formik.touched.status && Boolean(formik.errors.status)}
                  helperText={formik.touched.status && formik.errors.status}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Update status
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
