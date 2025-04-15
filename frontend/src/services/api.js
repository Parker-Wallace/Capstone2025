// src/services/api.js
import axios from 'axios';
import mockApi from './mockApi';

// Create Axios instance
const api = axios.create({
  baseURL: '/api', // Relative URL for now
});

// Intercept requests to use mockApi in development
api.interceptors.request.use(async (config) => {
    const urlPath = config.url.startsWith('/api/') ? config.url.replace('/api/', '') : config.url;
    if (urlPath === 'quote' && config.method === 'post') {
      const response = await mockApi.post('/api/quote', config.data);
      return { ...config, adapter: () => Promise.resolve(response) };
    }
    if (urlPath === 'policies/me' && config.method === 'get') {
      const response = await mockApi.get('/policies/me');
      return { ...config, adapter: () => Promise.resolve(response) };
    }
    if (urlPath === 'users' && config.method === 'post') {
      const response = await mockApi.post('/api/users', config.data);
      return { ...config, adapter: () => Promise.resolve(response) };
    }
    if (urlPath === 'login' && config.method === 'post') {
        const response = await mockApi.post('/api/login', config.data);
        return { ...config, adapter: () => Promise.resolve(response) };
      }
    return config;
  });

export default api;