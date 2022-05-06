import React from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import { Divider, List, ListItem, ListItemText } from "@mui/material";
import { useNavigate } from "react-router-dom";

const AdminPage = () => {
  const navigate = useNavigate();
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
        <hr />
        <List component="nav" aria-label="mailbox folders">
          <ListItem button>
            <ListItemText
              onClick={() => {
                navigate("/admin/games");
              }}
              primary="Games"
            />
          </ListItem>
          <Divider />
          <ListItem button divider>
            <ListItemText
              onClick={() => {
                navigate("/admin/orders");
              }}
              primary="Orders"
            />
          </ListItem>
        </List>
      </Container>
    </>
  );
};

export default AdminPage;
