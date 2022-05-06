import Box from "@mui/material/Box";
import React, { useContext } from "react";
// import hero from "../assets/hero.jpg";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import AuthContext from "../context/AuthContext";

const Hero = () => {
  const { isLogged } = useContext(AuthContext);
  const hero = require("../assets/hero.jpg");

  return (
    <Box
      sx={{
        backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(${hero})`,
        height: "80vh",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
        position: "relative",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        color: "#fff",
      }}
    >
      <Box sx={{ textAlign: "center" }}>
        <Typography variant="h1" component="h1">
          Games Center
        </Typography>
        <Typography variant="h5" component="h5" marginTop={1}>
          Buy your favourite games
        </Typography>
        {isLogged ? (
          <Button
            variant="contained"
            sx={{ marginTop: "20px" }}
            href={"/games"}
          >
            Buy a game
          </Button>
        ) : (
          <>
            <Button
              variant="contained"
              sx={{ marginTop: "20px" }}
              href={"/login"}
            >
              Login
            </Button>
            <Button
              variant="contained"
              sx={{ marginTop: "20px", marginLeft: "20px" }}
              href={"/register"}
            >
              Register
            </Button>
          </>
        )}
      </Box>
    </Box>
  );
};

export default Hero;
