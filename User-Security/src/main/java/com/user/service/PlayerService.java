package com.user.service;

import com.user.DTO.PlayerDTO;
import com.user.apis.COCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PlayerService {


    static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjYzMWFiM2MyLTY3YTgtNGE3OS05NDZlLWE2YzNhY2ZlNTE5NCIsImlhdCI6MTc0MzQ4NDQ1Mywic3ViIjoiZGV2ZWxvcGVyLzRhN2ZhNWU4LWNiZTYtYjcwMi0zOTEwLTZlNmZmNzNhODFkMyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjQ5LjM2LjkxLjY1Il0sInR5cGUiOiJjbGllbnQifV19.PLSXGc4y5tvggMz6MNoW7vb3UNlu4PNnugbrBztj53mQWITkEebC48G1Qo9Kzy3UuwsppnhPNmITUy3DAa5-aw";
    //static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjNiOGM4OWJlLWQ1MmMtNDY0Ny1iMmNhLTViMWVlNzE1OTU5YyIsImlhdCI6MTc0MzA5OTAxOSwic3ViIjoiZGV2ZWxvcGVyLzRhN2ZhNWU4LWNiZTYtYjcwMi0zOTEwLTZlNmZmNzNhODFkMyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE1Mi41OC42MC4xNTYiXSwidHlwZSI6ImNsaWVudCJ9XX0.JOSrrYr2D5o3RRroIC47Man5m5ZfHxef2d0AHVUzARIzFpINoSXuixF5syDvmUZY3UpZ-tseRqFoEHXOZPF22A";
    //static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjM0NTFmNjZmLWQzZTQtNGYxOS1iMjBkLTliOTcxMWE0OWIwYiIsImlhdCI6MTc0MzA5OTM1MCwic3ViIjoiZGV2ZWxvcGVyLzRhN2ZhNWU4LWNiZTYtYjcwMi0zOTEwLTZlNmZmNzNhODFkMyIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE1Mi41OC42MS4xMDkiXSwidHlwZSI6ImNsaWVudCJ9XX0.EefrqShzE8h8zbB_bdigHib7AbR3Vqw04jrVKrawtn7W-GXbH5S-68YZaXhd8BfpqRqO7NquhLnf7PDnDC_AWA";


    @Autowired
    RestTemplate restTemplate;

    public COCResponse getPlayerData(String playerTag)
    {
        String tag = playerTag.startsWith("#") ? playerTag : "#" + playerTag;


        //URI - Helps to fetch the data from the external APIs
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://api.clashofclans.com/v1/players/{playerTag}")
                .buildAndExpand(tag)
                .encode()
                .toUri();

        System.out.println("Final URI : " + uri.toString());


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_TOKEN);
        System.out.println("Authorization Header: " + headers.get("Authorization"));


        ResponseEntity<COCResponse> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), COCResponse.class);
        return response.getBody();

        //return response.getBody();
    }

    public PlayerDTO playerDTO(COCResponse cocResponse)
    {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerTag(cocResponse.getTag());
        playerDTO.setPlayerName(cocResponse.getName());
        playerDTO.setTownHallLevel(cocResponse.getTownHallLevel());
        playerDTO.setCurrentTrophies(cocResponse.getTrophies());
        playerDTO.setBestTrophies(cocResponse.getBestTrophies());
        playerDTO.setWarStars(cocResponse.getWarStars());
        playerDTO.setBuilderHallLevel(cocResponse.getBuilderHallLevel());
        playerDTO.setBuilderHallTrophies(cocResponse.getBuilderBaseTrophies());
        playerDTO.setBestBuilderHallTrophies(cocResponse.getBestBuilderBaseTrophies());

        return playerDTO;

    }

}
