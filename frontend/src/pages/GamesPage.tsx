import React, { useState } from "react";
import useApi from "../api/useApi";
import { useQuery, UseQueryResult } from "react-query";
import { IGameList } from "../api/interfaces/IGameList";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import GameCard from "../components/GameCard";
import {useSelector} from "react-redux";
import {RootState} from "../app/store";

const GamesPage = () => {
  const api = useApi();
  const [page, setPage] = useState(0);
  const searchValue = useSelector((state: RootState) => state.search.value);

  interface keys {
    queryKey: (string | number)[];
  }

  const fetchGames = async ({ queryKey }: keys): Promise<IGameList> => {
    const response = await api.get(`api/products?page=${queryKey[1]}`);
    return response.data;
  };

  const { data, isLoading, isError }: UseQueryResult<IGameList, Error> =
    useQuery<IGameList, Error, IGameList, (string | number)[]>(
      ["games", page],
      fetchGames,
      {
        keepPreviousData: true,
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
          sx={{ display: "flex", justifyContent: "center" }}
        >
          Games available in the stock:
        </Typography>
        <Box
          sx={{
            marginTop: 3,
            marginBottom: 4,
            display: "flex",
            justifyContent: "center",
          }}
        >
          <Button
            variant="contained"
            sx={{ minWidth: "100px" }}
            disabled={page === 0}
            onClick={() => setPage((old) => old - 1)}
          >
            Previous
          </Button>
          <Button
            variant="contained"
            sx={{ marginLeft: 3, minWidth: "100px" }}
            disabled={data?.totalPages === page + 1}
            onClick={() => setPage((old) => old + 1)}
          >
            Next
          </Button>
        </Box>
        <Grid container spacing={5}>
          {data?.content?.filter((game) => {
            if (searchValue === "") {
              return game;
            } else if (
              game.name.toLowerCase().includes(searchValue.toLowerCase())
            ) {
              return game;
            }
          }).map((game, id) => (
            <GameCard key={id} game={game} />
          ))}
        </Grid>
      </Container>
    </>
  );
};

export default GamesPage;
