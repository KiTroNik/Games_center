import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import SportsEsportsIcon from "@mui/icons-material/SportsEsports";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router-dom";
import IconButton from "@mui/material/IconButton";
import AccountCircle from "@mui/icons-material/AccountCircle";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import {useDispatch} from "react-redux";
import {Search, SearchIconWrapper, StyledInputBase} from "./Search";
import SearchIcon from "@mui/icons-material/Search";
import {changeSearch} from "../features/searchSlice";

export default function ButtonAppBar() {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const location = useLocation();
  const navigate = useNavigate();
  const { setIsLogged, setAuthTokens, isLogged, isAdmin, setIsAdmin } =
    useContext(AuthContext);
  const isMenuOpen = Boolean(anchorEl);
  const dispatch = useDispatch();

  const logout = () => {
    setIsLogged(false);
    setAuthTokens(null);
    setIsAdmin(false);
    localStorage.removeItem("authTokens");
    toast.success("Successfully logged out", {
      position: toast.POSITION.TOP_CENTER,
    });
    navigate("/");
  };

  const handleProfileMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const menuId = "primary-search-account-menu";
  const renderMenu = (
    <Menu
      anchorEl={anchorEl}
      anchorOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      id={menuId}
      keepMounted
      transformOrigin={{
        vertical: "top",
        horizontal: "right",
      }}
      open={isMenuOpen}
      onClose={handleMenuClose}
    >
      <MenuItem
        onClick={() => {
          setAnchorEl(null);
          navigate("/cart");
        }}
      >
        Cart
      </MenuItem>
      <MenuItem
        onClick={() => {
          setAnchorEl(null);
          navigate("/orders");
        }}
      >
        Orders
      </MenuItem>
    </Menu>
  );

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <SportsEsportsIcon sx={{ fontSize: 40 }} />
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1 }}
            marginLeft={3}
          >
            <Link href="/" underline="none" color="inherit">
              Games Center
            </Link>
          </Typography>
          {(location.pathname === "/games" || location.pathname === "/admin/games") && (
            <>
              <Search>
                <SearchIconWrapper>
                  <SearchIcon />
                </SearchIconWrapper>
                <StyledInputBase
                  placeholder="Search…"
                  inputProps={{ "aria-label": "search" }}
                  onChange={(e) => {
                    dispatch(changeSearch(e.target.value));
                  }}
                />
              </Search>
              <Box sx={{ flexGrow: 1 }} />
            </>
          )}
          {isLogged ? (
            <>
              <Button color="inherit" onClick={logout}>
                Logout
              </Button>
              {!isAdmin && (
                <Box sx={{ display: { xs: "none", md: "flex" } }}>
                  <IconButton
                    size="large"
                    edge="end"
                    aria-label="account of current user"
                    aria-controls={menuId}
                    aria-haspopup="true"
                    onClick={handleProfileMenuOpen}
                    color="inherit"
                  >
                    <AccountCircle />
                  </IconButton>
                </Box>
              )}
            </>
          ) : (
            <>
              <Button color="inherit" href="/login">
                Login
              </Button>
              <Button color="inherit" href="/register">
                Sign Up
              </Button>
            </>
          )}
        </Toolbar>
      </AppBar>
      {renderMenu}
    </Box>
  );
}
