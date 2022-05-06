import React from "react";
import { useParams } from "react-router-dom";
import { useQuery, UseQueryResult } from "react-query";
import Box from "@mui/material/Box";
import GameInfo from "../components/GameInfo";
import useApi from "../api/useApi";
import { IGame } from "../api/interfaces/IGame";

const Game = () => {
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

  if (isLoading) {
    return <Box>Loading...</Box>;
  }

  if (isError) {
    return <Box>Error</Box>;
  }

  return (
    <GameInfo
      id={parseInt(id as string)}
      name={data?.name as string}
      description={data?.description as string}
      price={data?.price as number}
      imageURL={data?.imageURL as string}
    />
  );
};

export default Game;
