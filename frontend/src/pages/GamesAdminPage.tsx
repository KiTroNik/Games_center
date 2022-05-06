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
import { IGame } from "../api/interfaces/IGame";
import { useMutation, useQuery, UseQueryResult } from "react-query";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import {useSelector} from "react-redux";
import {RootState} from "../app/store";

const LIST_GAMES_URL = "api/products/all";

const GamesAdminPage = () => {
  const [changed, setChanged] = useState(0);
  const searchValue = useSelector((state: RootState) => state.search.value);
  const api = useApi();
  const navigate = useNavigate();

  const fetchGames = async (): Promise<IGame[]> => {
    const response = await api.get(LIST_GAMES_URL);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IGame[], Error> = useQuery<
    IGame[],
    Error
  >(["all_games", changed], fetchGames);

  const { mutateAsync } = useMutation(
    (id: number) => api.delete(`api/products/${id}`),
    {
      onSuccess: () => {
        toast.success("Successfully deleted", {
          position: toast.POSITION.TOP_CENTER,
        });
      },
    }
  );

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
        <Button
          variant="contained"
          color="success"
          sx={{ marginBottom: 5 }}
          onClick={() => {
            navigate("/admin/games/add");
          }}
        >{`Add`}</Button>
        <hr />
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
                <TableCell align="right">Price</TableCell>
                <TableCell align="right">Edit</TableCell>
                <TableCell align="right">Delete</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {/* eslint-disable-next-line array-callback-return */}
              {data?.filter((game) => {
                if (searchValue === "") {
                  return game;
                } else if (
                  game.name.toLowerCase().includes(searchValue.toLowerCase())
                ) {
                  return game;
                }
              }).map((game) => (
                <TableRow
                  key={game.id}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {game.name}
                  </TableCell>
                  <TableCell align="right">${game.price}</TableCell>
                  <TableCell align="right">
                    <Button
                      variant="contained"
                      onClick={() => {
                        navigate(`/admin/games/${game.id}`);
                      }}
                    >{`edit`}</Button>
                  </TableCell>
                  <TableCell align="right">
                    <Button
                      variant="contained"
                      color="error"
                      onClick={() => {
                        mutateAsync(game.id).then((r) =>
                          setChanged(changed + 1)
                        );
                      }}
                    >{`delete`}</Button>
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

export default GamesAdminPage;
