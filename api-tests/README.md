# API Tests (Bruno collection)

A [Bruno](https://www.usebruno.com) collection for the E-Commerce Service REST API.
Everything is plain text and version-controlled alongside the code.

## Install Bruno

```
brew install bruno
```

(or download from https://www.usebruno.com)

## Run

1. Start the app: `mvn spring-boot:run` (listens on `http://localhost:8080`).
2. In Bruno: **Open Collection** → select this `api-tests` folder.
3. Pick the **Local** environment (top-right). It defines `baseUrl=http://localhost:8080`.
4. Run requests individually, or use **Run Collection** to run them all.

## Layout

- **Users** – create / list / get by id / get by email / update / delete
- **Products** – create / list / get by id / get by SKU / get by category / update / delete
- **Orders** – create / list / get by id / by user / by status / update status / cancel

## Chaining

`Create User`, `Create Product`, and `Create Order` automatically save the
returned `id` into the `userId` / `productId` / `orderId` environment variables,
so the follow-up requests target the records you just created. Recommended order:

1. Users → **Create User**
2. Products → **Create Product**
3. Orders → **Create Order** (uses the user + product above), then the rest.

Order status values: `PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, REFUNDED`.
