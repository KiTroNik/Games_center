import React from "react";
import Grid from "@mui/material/Grid";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { Paper } from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { useNavigate } from "react-router-dom";
import { IGame } from "../api/interfaces/IGame";

const theme = createTheme();

interface GameCardProps {
  game: IGame;
}

const GameCard = ({ game }: GameCardProps) => {
  const navigate = useNavigate();

  return (
    <Grid item xs={4} md={3} className="game">
      <ThemeProvider theme={theme}>
        <Paper
          elevation={24}
          className="paper"
          onClick={() => navigate(`/game/${game.id}`)}
        >
          <img src={game.imageURL} alt="" className="img" />
          <Box sx={{ paddingX: 1 }}>
            <Typography variant="h6" component="h2">
              {game.name}
            </Typography>
          </Box>
          <Box sx={{ display: "flex", paddingX: 1 }}>
            <Typography variant="subtitle1" component="h2" marginTop={0}>
              {game.price}$
            </Typography>
          </Box>
        </Paper>
      </ThemeProvider>
    </Grid>
  );
};

export default GameCard;
