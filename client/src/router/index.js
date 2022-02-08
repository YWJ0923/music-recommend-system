import React from 'react';
import { Redirect } from 'react-router-dom';

const TopList = React.lazy(() => import('../pages/TopList'));
const Discover = React.lazy(() => import('../pages/Discover'));
const Album = React.lazy(() => import('../pages/Album'));
const Artist = React.lazy(() => import('../pages/Artist'));
const ArtistList = React.lazy(() => import('../pages/ArtistList'));
const MyMusic = React.lazy(() => import('../pages/MyMusic'));
const Ranking = React.lazy(() => import('../pages/MyMusic/Ranking'));
const Recommend = React.lazy(() => import('../pages/MyMusic/Recommend'));
const CollectionList = React.lazy(() => import('../pages/MyMusic/CollectionList'));
const Search = React.lazy(() => import('../pages/Search'));
const Error = React.lazy(() => import('../pages/Error'));

const routes = [
    {path: '/', exact: true, render: () => <Redirect to="/discover" />},
    {path: '/artist/:artistId', component: Artist},
    {path: '/album/:albumId', component: Album},
    {path: '/search', component: Search},
    // {path: '/artist_list', exact: true, render: () => <Redirect to="/artist_list?artist_location=hot" />},
    {path: '/artist_list', component: ArtistList},
    {path: '/my_music', component: MyMusic, routes: [
        {path: '/my_music', exact: true, render: () => <Redirect to="/my_music/ranking" />},
        {path: '/my_music/ranking', component: Ranking},
        {path: '/my_music/recommend', component: Recommend},
        {path: '/my_music/collection_list/:collection_list_id', component: CollectionList}
    ]},
    {path: '/top_list', component: TopList},
    {path: '/discover', component: Discover},
    {path: '/error', component: Error}
];

export default routes;
