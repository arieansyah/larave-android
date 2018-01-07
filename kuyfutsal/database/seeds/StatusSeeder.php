<?php

use Illuminate\Database\Seeder;

class StatusSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
      DB::table('jadwals')->insert(array(
        [
         'id_jadwal' => '1',
        ],
        [
         'id_jadwal' => '2',
        ],
        [
         'id_jadwal' => '3',
        ],
        [
         'id_jadwal' => '4',
        ],
        [
         'id_jadwal' => '5',
        ],
        [
         'id_jadwal' => '6',
        ],
        [
         'id_jadwal' => '7',
        ],
        [
         'id_jadwal' => '8',
        ],
        [
         'id_jadwal' => '9',
        ],
        [
         'id_jadwal' => '10',
        ],
        [
         'id_jadwal' => '11',
        ],
        [
         'id_jadwal' => '12',
        ],
        [
         'id_jadwal' => '13',
        ],
        [
         'id_jadwal' => '2',
        ],
        [
         'id_jadwal' => '2',
        ],
        [
         'id_jadwal' => '2',
        ]
      ));
    }
}
