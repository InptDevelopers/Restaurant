# Use an official Node.js runtime as the base image
FROM node:18-alpine as build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package.json yarn.lock ./

# Install dependencies
RUN yarn install --frozen-lockfile

# Copy the rest of the application code
COPY . .

# Build the React app
RUN yarn build

# Stage 2: Serve the production build with Nginx
FROM nginx:alpine

# Copy the built React app from the 'build' stage to serve it with Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80 to the outside world
EXPOSE 5173

# Command to run the Nginx server
CMD ["nginx", "-g", "daemon off;"]
