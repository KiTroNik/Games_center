import Typography from "@mui/material/Typography";
import * as React from "react";

export default function Copyright() {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      marginBottom={4}
      sx={{mt: 5}}
    >
      {"Copyright © Jakub Tomala & Arkadiusz Wieteska "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}
