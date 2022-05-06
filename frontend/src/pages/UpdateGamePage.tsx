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

const theme = createTheme();

export default function UpdateGamePage() {
  const navigate = useNavigate();
  const api = useApi();
  const { id } = useParams();

  const fetchGame = async (): Promise<IGame> => {
    const response = await api.get(`/api/products/${id}`);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IGame, Error> = useQuery<
    IGame,
    Error
  >("game", fetchGame);

  const validationSchema = yup.object({
    name: yup.string().required("Name is required."),
    description: yup.string().required("Description is required."),
    price: yup
      .number()
      .required("Price is required")
      .min(3, "Price can't be lower than $3"),
    imageUrl: yup.string().required("Image Url is required"),
  });

  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      name: data?.name,
      description: data?.description,
      price: data?.price,
      imageUrl: data?.imageURL,
    },
    validationSchema: validationSchema,
    onSubmit: async (values, { setErrors }) => {
      try {
        await api.put(
          `/api/products/${data?.id}`,
          JSON.stringify({
            name: values.name,
            description: values.description,
            price: values.price,
            imageURL: values.imageUrl,
          })
        );
        toast.success("Product updated :)", {
          position: toast.POSITION.TOP_CENTER,
        });
        navigate("/admin/games");
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
            Update product
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
                  id="name"
                  label="Game name"
                  name="name"
                  autoComplete="Game name"
                  value={formik.values.name}
                  onChange={formik.handleChange}
                  error={formik.touched.name && Boolean(formik.errors.name)}
                  helperText={formik.touched.name && formik.errors.name}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="price"
                  label="Price"
                  name="price"
                  autoComplete="price"
                  type="number"
                  value={formik.values.price}
                  onChange={formik.handleChange}
                  error={formik.touched.price && Boolean(formik.errors.price)}
                  helperText={formik.touched.price && formik.errors.price}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  id="imageUrl"
                  label="Image URL"
                  name="imageUrl"
                  autoComplete="Image URL"
                  value={formik.values.imageUrl}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.imageUrl && Boolean(formik.errors.imageUrl)
                  }
                  helperText={formik.touched.imageUrl && formik.errors.imageUrl}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  name="description"
                  label="Description"
                  id="description"
                  autoComplete="Description"
                  multiline
                  value={formik.values.description}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.description &&
                    Boolean(formik.errors.description)
                  }
                  helperText={
                    formik.touched.description && formik.errors.description
                  }
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Update Product
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
