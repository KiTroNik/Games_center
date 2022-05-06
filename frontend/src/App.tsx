import React from "react";
import { AuthProvider } from "./context/AuthContext";
import { ToastContainer } from "react-toastify";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Copyright from "./components/Copyright";
import HomePage from "./pages/HomePage";
import UserLoggedRoute from "./utils/UserLoggedRoute";
import SignIn from "./pages/SignInPage";
import SignUp from "./pages/SignUpPage";
import { QueryClient, QueryClientProvider } from "react-query";
import PrivateRoute from "./utils/PrivateRoute";
import GamesPage from "./pages/GamesPage";
import GamePage from "./pages/GamePage";
import CartPage from "./pages/CartPage";
import PlaceOrderPage from "./pages/PlaceOrderPage";
import OrdersPage from "./pages/OrdersPage";
import AdminRoute from "./utils/AdminRoute";
import AdminPage from "./pages/AdminPage";
import GamesAdminPage from "./pages/GamesAdminPage";
import AddGamePage from "./pages/AddGamePage";
import UpdateGamePage from "./pages/UpdateGamePage";
import OrdersAdminPage from "./pages/OrdersAdminPage";
import UpdateOrderPage from "./pages/UpdateOrderPage";

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <ToastContainer />
        <AuthProvider>
          <Header />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route
              path="/login"
              element={
                <UserLoggedRoute>
                  <SignIn />
                </UserLoggedRoute>
              }
            />
            <Route
              path="/register"
              element={
                <UserLoggedRoute>
                  <SignUp />
                </UserLoggedRoute>
              }
            />
            <Route
              path="/games"
              element={
                <PrivateRoute>
                  <GamesPage />
                </PrivateRoute>
              }
            />
            <Route
              path="/game/:id"
              element={
                <PrivateRoute>
                  <GamePage />
                </PrivateRoute>
              }
            />
            <Route
              path="/cart"
              element={
                <PrivateRoute>
                  <CartPage />
                </PrivateRoute>
              }
            />
            <Route
              path="/place_order"
              element={
                <PrivateRoute>
                  <PlaceOrderPage />
                </PrivateRoute>
              }
            />
            <Route
              path="/orders"
              element={
                <PrivateRoute>
                  <OrdersPage />
                </PrivateRoute>
              }
            />
            <Route
              path="/admin"
              element={
                <AdminRoute>
                  <AdminPage />
                </AdminRoute>
              }
            />
            <Route
              path="/admin/games"
              element={
                <AdminRoute>
                  <GamesAdminPage />
                </AdminRoute>
              }
            />
            <Route
              path="/admin/games/add"
              element={
                <AdminRoute>
                  <AddGamePage />
                </AdminRoute>
              }
            />
            <Route
              path="/admin/games/:id"
              element={
                <AdminRoute>
                  <UpdateGamePage />
                </AdminRoute>
              }
            />
            <Route
              path="/admin/orders"
              element={
                <AdminRoute>
                  <OrdersAdminPage />
                </AdminRoute>
              }
            />
            <Route
              path="/admin/orders/:id"
              element={
                <AdminRoute>
                  <UpdateOrderPage />
                </AdminRoute>
              }
            />
          </Routes>
          <Copyright />
        </AuthProvider>
      </BrowserRouter>
    </QueryClientProvider>
  );
}

export default App;
