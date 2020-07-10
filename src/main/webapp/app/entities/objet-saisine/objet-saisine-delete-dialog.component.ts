import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IObjetSaisine } from 'app/shared/model/objet-saisine.model';
import { ObjetSaisineService } from './objet-saisine.service';

@Component({
  templateUrl: './objet-saisine-delete-dialog.component.html',
})
export class ObjetSaisineDeleteDialogComponent {
  objetSaisine?: IObjetSaisine;

  constructor(
    protected objetSaisineService: ObjetSaisineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.objetSaisineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('objetSaisineListModification');
      this.activeModal.close();
    });
  }
}
